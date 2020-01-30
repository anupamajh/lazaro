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
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter, ticketId:String) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getTicket(ticketId: ticketId) { (response) in
            self.ticket = response.taskTicket!
            self.ticketResponse = response
        }
    }
    
    func getTicket(ticketId:String, completionHandler: @escaping(TicketResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let parameters = [
                    "id" : ticketId
                ]
                AF.request(EndPoints.TICKET_GET_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        if(response.data != nil){
                            do{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(TicketResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }catch{
                                let parsedData = TicketResponse()
                                parsedData.error = "Unknow error has occurred!"
                                completionHandler(parsedData)
                            }
                        }else{
                            let parsedData = TicketResponse()
                            parsedData.error = "Unknow error has occurred!"
                            completionHandler(parsedData)
                        }
                }
            }
        }
    }
}
