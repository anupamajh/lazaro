//
//  TicketGetService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class TicketGetService:ObservableObject{
    @Published var ticketResponse = TicketResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var ticket:Ticket = Ticket()
    
    var loginService = LoginService()
    
    init(viewRouter: ViewRouter, ticketId:String) {
        self.viewRouter = viewRouter;
        self.getTicket(ticketId: ticketId) { (response) in
            self.ticket = response.taskTicket!
        }
    }
    
        func getTicket(ticketId:String, completionHandler: @escaping(TicketResponse)->Void) -> Void {
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
                    "id" : ticketId
                ]
                AF.request(EndPoints.TICKET_GET_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        let parsedData = try! jsonDecoder.decode(TicketResponse.self, from: response.data!)
                        completionHandler(parsedData)
                }
            }
        }
    }
    
    
}
