//
//  GetUserInterests.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 23/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class GetUserInterests:ObservableObject{
    @Published var userInterestsResponse = UserInterestsResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var userInterestList:[UserInterests] = []
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getTicketList { (response) in
            self.userInterestsResponse = response
            if(response.userInterestsList == nil){
                self.userInterestList = []
            }else{
                self.userInterestList = response.userInterestsList!;
            }
            self.fetchComplete = true
        }
    }
    
    func getTicketList(completionHandler: @escaping(UserInterestsResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["" : ""]
                AF.request(
                    EndPoints.GET_MY_INTEREST_LIST_URL,
                    method: .post,
                    parameters: parameters,
                    encoding: JSONEncoding.default,
                    headers: headers
                ).responseData { (response) in
                    do{
                        if(response.data != nil){
                            let jsonDecoder = JSONDecoder()
                            let parsedData = try jsonDecoder.decode(UserInterestsResponse.self, from: response.data!)
                            completionHandler(parsedData)
                        }else{
                            let parsedData = UserInterestsResponse();
                            parsedData.error = "Unknown error has occured"
                            completionHandler(parsedData)
                        }
                    }catch{
                        let parsedData = UserInterestsResponse();
                        parsedData.error = "Unknown error has occured"
                        completionHandler(parsedData)
                    }
                }
            }
            
        }
    }
}
