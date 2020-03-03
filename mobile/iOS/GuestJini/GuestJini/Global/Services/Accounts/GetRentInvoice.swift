//
//  GetRentInvoice.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/03/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class GetRentInvoice:ObservableObject{
    @Published var accountTicketResponse = AccountTicketResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var accountTicket:AccountTicket = AccountTicket()
    @Published var fetchComplete = false
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter, ticketId:String) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.fetchComplete = false
        self.getRentInvoice(ticketId: ticketId) { (response) in
            self.accountTicket = response.accountTicket!
            self.accountTicketResponse = response
        }
    }
    
    func getRentInvoice(ticketId:String, completionHandler: @escaping(AccountTicketResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let parameters = [
                    "id" : ticketId
                ]
                AF.request(EndPoints.ACCOUNT_GET_RENT_INVOICE, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        self.fetchComplete = true
                        if(response.data != nil){
                            do{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(AccountTicketResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }catch{
                                let parsedData = AccountTicketResponse()
                                parsedData.error = "Unknow error has occurred!"
                                completionHandler(parsedData)
                            }
                        }else{
                            let parsedData = AccountTicketResponse()
                            parsedData.error = "Unknow error has occurred!"
                            completionHandler(parsedData)
                        }
                }
            }
        }
    }
}
