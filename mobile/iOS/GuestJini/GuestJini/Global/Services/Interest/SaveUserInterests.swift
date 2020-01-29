//
//  SaveUserInterests.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 23/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class SaveUserInterests:ObservableObject{
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
    }
    
    func saveUserInterest(userInterest:UserInterests, completionHandler: @escaping(UserInterestsResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let jsonEncoder = JSONEncoder()
                let userInterestData = try! jsonEncoder.encode(userInterest)
                let json = try! JSONSerialization.jsonObject(with: userInterestData, options: []) as? [String : Any]
                AF.request(EndPoints.SAVE_MY_INTEREST_URL, method: .post, parameters: json,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            if(response.data != nil){
                                let parsedData =  try jsonDecoder.decode(UserInterestsResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }else{
                                let parsedData = UserInterestsResponse()
                                parsedData.error = "Unknow error has occrred"
                                parsedData.success = false;
                                completionHandler(parsedData)
                            }
                        }catch{
                            let parsedData = UserInterestsResponse()
                            parsedData.error = "Unknow error has occrred"
                            parsedData.success = false;
                            completionHandler(parsedData)
                        }
                        
                }
                
                
            }
        }
    }
    
}
