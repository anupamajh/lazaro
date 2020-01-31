//
//  ChangePasswordService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 31/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class ChangePasswordService:ObservableObject{
    @Published var genericResponse = GenericResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
    }
    
    func changePassword(currentPassword:String, newPassword:String, completionHandler: @escaping(GenericResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let parameters = [
                    "currentPassword" : currentPassword,
                    "newPassword" : newPassword
                ]
                AF.request(EndPoints.CHANGE_PASSWORD_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        if(response.data != nil){
                            do{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(GenericResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }catch{
                                let parsedData = GenericResponse()
                                parsedData.error = "Unknow error has occurred!"
                                completionHandler(parsedData)
                            }
                        }else{
                            let parsedData = GenericResponse()
                            parsedData.error = "Unknow error has occurred!"
                            completionHandler(parsedData)
                        }
                }
            }
        }
    }
}

