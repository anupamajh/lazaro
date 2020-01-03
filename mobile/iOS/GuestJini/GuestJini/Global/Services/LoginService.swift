//
//  LoginService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire

class LoginService:ObservableObject{
    @Published var authData = AuthData()
    
    init() {
        
    }
    
    func performLogin(UserName:String, Password:String, completionHandler:@escaping(AuthData)->Void) -> Void {
        let client_id = EndPoints.CLIENT_ID
        let client_secrete = EndPoints.CLIENT_SECRETE
        let headers: HTTPHeaders = [
            .authorization(username: client_id, password: client_secrete),
            .accept("application/x-www-form-urlencoded")
        ]
        
        let parameters = [
            "username" : UserName,
            "password" :Password,
            "grant_type" : "password"
        ]
        
        AF.request(EndPoints.AUTHORISATION_URL, method: .post, parameters: parameters, encoder: URLEncodedFormParameterEncoder.default,headers: headers).responseDecodable(completionHandler: {
            (response: DataResponse<AuthData, AFError>) in
            var authData = AuthData()
            if(response.value != nil){
                authData = try! response.result.get()
            }
            completionHandler(authData)
        })
        
    }
    
    func refreshToken(RefreshToken:String, completionHandler:@escaping(AuthData)->Void) -> Void {
        let client_id = EndPoints.CLIENT_ID
        let client_secrete = EndPoints.CLIENT_SECRETE
        let headers: HTTPHeaders = [
            .authorization(username: client_id, password: client_secrete),
            .accept("application/x-www-form-urlencoded")
        ]
        let parameters = [
                   "refresh_token" :RefreshToken,
                   "grant_type" : "refresh_token"
               ]
               
        AF.request(EndPoints.AUTHORISATION_URL, method: .post, parameters: parameters, encoder: URLEncodedFormParameterEncoder.default,headers: headers).responseDecodable(completionHandler: {
            (response: DataResponse<AuthData, AFError>) in
            var authData = AuthData()
            if(response.value != nil){
                authData = try! response.result.get()
            }
            completionHandler(authData)
        })
    }
}
