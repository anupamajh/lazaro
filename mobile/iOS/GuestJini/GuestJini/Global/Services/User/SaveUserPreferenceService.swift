//
//  SaveUserPreferenceService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 14/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class SaveUserPreferenceService: ObservableObject {
    
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        checkTokenService = CheckTokenService(viewRouter: viewRouter)
        
    }
    
    func saveUserPreference(userPreferenceType:Int, isHidden:Int, completionHandler:@escaping(UserPreferenceResponse)->Void)-> Void{
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let parameters:[String: Int] = [
                    "preferenceType" : userPreferenceType,
                    "isHidden" : isHidden
                ]
                
                AF.request(EndPoints.SAVE_USER_PREFERENCE_URL,
                           method: .post,
                           parameters: parameters,
                           encoding: JSONEncoding.default,
                           headers: headers
                )
                    .responseData { (response) in
                    
                        let jsonDecoder = JSONDecoder()
                        do{
                            let parsedData =  try jsonDecoder.decode(UserPreferenceResponse.self, from: response.data!)
                            completionHandler(parsedData)
                        }catch{
                            let parsedData = UserPreferenceResponse()
                            parsedData.error = "Unknow error has occrred"
                            parsedData.success = false;
                            completionHandler(parsedData)
                        }
                }
            }
        }
    }
    
}
