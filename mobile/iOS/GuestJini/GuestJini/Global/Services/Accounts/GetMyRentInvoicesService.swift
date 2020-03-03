//
//  GetMyRentInvoicesService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/03/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class GetMyRentInvoicesService:ObservableObject{
    @Published var accountTicketResponse = AccountTicketResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var accountTicketList:[AccountTicket] = []
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        if(Connectivity.isConnectedToInternet()){
            self.getMyRentInvoice { (response) in
                self.accountTicketResponse = response
                if(response.accountTicketList != nil){
                    self.accountTicketList = response.accountTicketList!;
                }else{
                    self.accountTicketList  = []
                }
                self.fetchComplete = true
            }
        }
    }
    
    func getMyRentInvoice(completionHandler: @escaping(AccountTicketResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["" : ""]
                AF.request(EndPoints.ACCOUNT_GET_MY_RENT_INVOICES, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        if(response.data != nil){
                            do{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(AccountTicketResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }catch{
                                let parsedData = AccountTicketResponse()
                                parsedData.error = "Unknown error has occurred!"
                                completionHandler(parsedData)
                            }
                        }else{
                            let parsedData = AccountTicketResponse()
                            parsedData.error = "Unknown error has occurred!"
                            completionHandler(parsedData)
                        }
                }
            }
            
        }
    }
}
