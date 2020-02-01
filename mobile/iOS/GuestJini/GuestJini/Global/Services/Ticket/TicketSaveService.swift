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
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
    }
    
    func saveTicket(ticketData:TaskTicketRequest, completionHandler: @escaping(TicketResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let jsonEncoder = JSONEncoder()
                let taskTicketData = try! jsonEncoder.encode(ticketData)
                let json = try! JSONSerialization.jsonObject(with: taskTicketData, options: []) as? [String : Any]
                 AF.request(EndPoints.TICKET_SAVE_URL, method: .post, parameters: json,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            if(response.data != nil){
                                let parsedData =  try jsonDecoder.decode(TicketResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }else{
                                let parsedData = TicketResponse()
                                parsedData.error = "Unknow error has occrred"
                                parsedData.success = false;
                                completionHandler(parsedData)
                            }
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
