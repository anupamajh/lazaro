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
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getTicketList { (response) in
            self.ticketResponse = response
            self.ticketList = response.taskTicketList!;
            self.fetchComplete = true
        }
    }
    
    func getTicketList(completionHandler: @escaping(TicketResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["" : ""]
                AF.request(EndPoints.TICKET_LIST_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        if(response.data != nil){
                            do{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(TicketResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }catch{
                                let parsedData = TicketResponse()
                                parsedData.error = "Unknown error has occurred!"
                                completionHandler(parsedData)
                            }
                        }else{
                            let parsedData = TicketResponse()
                            parsedData.error = "Unknown error has occurred!"
                            completionHandler(parsedData)
                        }
                }
            }
            
        }
    }
    
    
    
}
