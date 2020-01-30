//
//  AppAccessRequestService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 15/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class AppAccessRequestService:ObservableObject{
    @Published var userInfo = UserInfo()
    
    init() {
        
    }
    
    func requestAppAccess(email:String, mobile:String, completionHandler:@escaping(AppAccessRequestResponse)->Void) -> Void {
        let headers: HTTPHeaders = [
            "Accept": "application/json"
        ]
        
        let parameters = [
            "email":email,
            "mobile":mobile
        ]
        AF.request(EndPoints.APP_ACCESS_REQUEST_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers).responseData { (response) in
            let jsonDecoder = JSONDecoder()
            do{
                if(response.data != nil){
                    let parsedData = try jsonDecoder.decode(AppAccessRequestResponse.self, from: response.data!)
                    completionHandler(parsedData)
                }else{
                    let parsedData = AppAccessRequestResponse();
                    completionHandler(parsedData)
                }
            }catch{
                let parsedData = AppAccessRequestResponse();
                completionHandler(parsedData)
            }
        }
        
    }
    
}

