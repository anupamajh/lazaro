//
//  ForgotPasswordService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 06/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class ForgotPasswordService:ObservableObject{
    @Published var userInfo = UserInfo()
    
    init() {
        
    }
    
    func resetPassword(UserName:String, completionHandler:@escaping(ForgotPasswordResponse)->Void) -> Void {
        let headers: HTTPHeaders = [
            "Accept": "application/json"
        ]
        
        let parameters = [
            "user_name":UserName
        ]
        AF.request(EndPoints.FORGOT_PASSWORD, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers).responseData { (response) in
            let jsonDecoder = JSONDecoder()
            do{
                let parsedData = try jsonDecoder.decode(ForgotPasswordResponse.self, from: response.data!)
                completionHandler(parsedData)
            }catch{
                debugPrint(error)
                let parsedData = ForgotPasswordResponse();
                completionHandler(parsedData)
            }
        }
        
    }
    
}
