//
//  TicketsService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 02/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire

class TicketService:ObservableObject{
    @Published var ticketResponse = TicketResponse()
    
    var loginService = LoginService()
    
    init() {
        
    }
    
    func saveTicket(ticketData:Ticket, completionHandler: @escaping(TicketResponse)->Void) -> Void {
        loginService.refreshToken(RefreshToken: UserDefaults.standard.string(forKey: "refresh_token")!) { (authData) in
            UserDefaults.standard.set(true, forKey: "isLoggedIn")
            UserDefaults.standard.set(authData.access_token, forKey: "access_token")
            UserDefaults.standard.set(authData.refresh_token, forKey: "refresh_token")
            UserDefaults.standard.set(authData.token_type, forKey: "token_type")
            UserDefaults.standard.set(authData.expires_in, forKey: "expires_in")
            
            let headers: HTTPHeaders = [
                "Authorization": "Bearer \(authData.access_token)",
                "Accept": "application/json"
            ]
            
            let parameters = [
                ticketData
            ]
            AF.request(EndPoints.TICKET_SAVE_URL, method: .post, parameters: parameters, encoder: URLEncodedFormParameterEncoder.default,headers: headers).responseDecodable(completionHandler: {
                (response: DataResponse<TicketResponse, AFError>) in
                var ticketResponse = TicketResponse()
                if(response.value != nil){
                    ticketResponse = try! response.result.get()
                }
                completionHandler(ticketResponse)
            })
            
        }
    }
    
    /*
     /*
     let jsonDecoder = JSONDecoder()
     let parsedData = try jsonDecoder.decode(T.self, from: data)
     */
     
     AF.request(EndPoints.TICKET_GET_URL, method: .post, parameters: parameters,headers: headers)
     .responseData { (respose) in
     let jsonDecoder = JSONDecoder()
     let parsedData = try! jsonDecoder.decode(TicketResponse.self, from: respose.data!)
     
     completionHandler(parsedData)
     })
     
     */
    
    func getTicket(ticketId:String, completionHandler: @escaping(TicketResponse)->Void) -> Void {
        loginService.refreshToken(RefreshToken: UserDefaults.standard.string(forKey: "refresh_token")!) { (authData) in
            UserDefaults.standard.set(true, forKey: "isLoggedIn")
            UserDefaults.standard.set(authData.access_token, forKey: "access_token")
            UserDefaults.standard.set(authData.refresh_token, forKey: "refresh_token")
            UserDefaults.standard.set(authData.token_type, forKey: "token_type")
            UserDefaults.standard.set(authData.expires_in, forKey: "expires_in")
            
            let headers: HTTPHeaders = [
                "Authorization": "Bearer \(authData.access_token)",
                "Accept": "application/json"
            ]
            let parameters = [
                "id" : ticketId
            ]
            AF.request(EndPoints.TICKET_GET_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                .responseData { (response) in
                    let jsonDecoder = JSONDecoder()
                    let parsedData = try! jsonDecoder.decode(TicketResponse.self, from: response.data!)
                    completionHandler(parsedData)
            }
        }
    }
    
    func getTicketList(completionHandler: @escaping(TicketResponse)->Void) -> Void {
        loginService.refreshToken(RefreshToken: UserDefaults.standard.string(forKey: "refresh_token")!) { (authData) in
            UserDefaults.standard.set(true, forKey: "isLoggedIn")
            UserDefaults.standard.set(authData.access_token, forKey: "access_token")
            UserDefaults.standard.set(authData.refresh_token, forKey: "refresh_token")
            UserDefaults.standard.set(authData.token_type, forKey: "token_type")
            UserDefaults.standard.set(authData.expires_in, forKey: "expires_in")
            
            let headers: HTTPHeaders = [
                "Authorization": "Bearer \(authData.access_token)",
                "Accept": "application/json"
            ]
            
            let parameters = ["" : ""]
            AF.request(EndPoints.TICKET_LIST_URL, method: .post, parameters: parameters, encoder: URLEncodedFormParameterEncoder.default,headers: headers).responseDecodable(completionHandler: {
                (response: DataResponse<TicketResponse, AFError>) in
                var ticketResponse = TicketResponse()
                if(response.value != nil){
                    ticketResponse = try! response.result.get()
                }
                completionHandler(ticketResponse)
            })
        }
    }
    
}
