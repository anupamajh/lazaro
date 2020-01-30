//
//  SaveProfilePicService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 14/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class SaveProfilePicService: ObservableObject {
    
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        checkTokenService = CheckTokenService(viewRouter: viewRouter)
        
    }
    
    func saveProfilePic(imageData:String, completionHandler:@escaping(UserInfo)->Void)-> Void{
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let parameters:[String: String] = [
                    "imageData" : imageData
                ]
                
                AF.request(EndPoints.SAVE_PROFILE_PIC_URL,
                           method: .post,
                           parameters: parameters,
                           encoding: JSONEncoding.default,
                           headers: headers
                )
                    .responseData { (response) in
                        
                        let jsonDecoder = JSONDecoder()
                        do{
                            if(response.data != nil){
                                let parsedData =  try jsonDecoder.decode(UserInfo.self, from: response.data!)
                                completionHandler(parsedData)
                            }else{
                                let parsedData = UserInfo()
                                completionHandler(parsedData)
                            }
                        }catch{
                            let parsedData = UserInfo()
                            completionHandler(parsedData)
                        }
                }
            }
        }
    }
    
}
