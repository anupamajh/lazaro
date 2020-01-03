//
//  TicketListService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class TicketListService:ObservableObject{
    @Published var ticketResponse = TicketResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var ticketList:[Ticket] = []
    
    var loginService = LoginService()
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.getTicketList { (response) in
            self.ticketResponse = response
            self.ticketList = response.taskTicketList!;
        }
    }
    
    func getTicketList(completionHandler: @escaping(TicketResponse)->Void) -> Void {
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
                 
                 let parameters = ["" : ""]
                 AF.request(EndPoints.TICKET_LIST_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                     .responseData { (response) in
                         let jsonDecoder = JSONDecoder()
                         let parsedData = try! jsonDecoder.decode(TicketResponse.self, from: response.data!)
                         completionHandler(parsedData)
                 }
             }
             
         }
     }
    
    
    
}
