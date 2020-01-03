//
//  TicketSaveService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class TicketSaveService:ObservableObject{
    @ObservedObject var viewRouter: ViewRouter
    var loginService = LoginService()
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
    }
    
    func saveTicket(ticketData:Ticket, completionHandler: @escaping(TicketResponse)->Void) -> Void {
        loginService.refreshToken(RefreshToken: UserDefaults.standard.string(forKey: "refresh_token")!) { (authData) in
            if(authData.access_token.trimmingCharacters(in: .whitespacesAndNewlines)==""){
                UserDefaults.standard.set(false, forKey: "isLoggedIn")
                self.viewRouter.currentPage = ViewRoutes.LOGIN_PAGE
            }else{
                UserDefaults.standard.set(true, forKey: "isLoggedIn")
                UserDefaults.standard.set(authData.access_token, forKey: "access_token")
                UserDefaults.standard.set(authData.refresh_token, forKey: "refresh_token")
                UserDefaults.standard.set(authData.token_type, forKey: "token_type")
                UserDefaults.standard.set(authData.expires_in, forKey: "expires_in")
                
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(authData.access_token)",
                    "Accept": "application/json"
                ]
                
                let parameters = [
                    "ticketTitle" : ticketData.ticketTitle,
                    "ticketNarration" : ticketData.ticketNarration
                ]
                AF.request(EndPoints.TICKET_SAVE_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            let parsedData =  try jsonDecoder.decode(TicketResponse.self, from: response.data!)
                            completionHandler(parsedData)
                        }catch{
                            let parsedData = TicketResponse()
                            parsedData.error = "Unknow error has occrred"
                            parsedData.success = false;
                            completionHandler(parsedData)
                        }
                        
                }
                
                
            }
        }
    }
    
}
