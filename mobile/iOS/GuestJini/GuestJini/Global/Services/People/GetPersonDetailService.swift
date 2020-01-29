//
//  GetPersonDetailService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 29/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class GetPersonDetailService:ObservableObject{
    @Published var peopleResponse = PeopleResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var myTotalInterests:Int = 0;
    @Published var fetchComplete:Bool = false
    @Published var othersAddressBook:AddressBook = AddressBook()
    @Published var othersInfo:UserInfo = UserInfo()
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter, userId:String) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getPersonDetail(userId: userId) { (response) in
            self.peopleResponse = response
            if(response.myInterests != nil){
                self.myTotalInterests = response.myInterests!.count
            }
            self.othersAddressBook = response.othersAddressBook!
            self.othersInfo = response.otherUserInfo!
            self.fetchComplete = true
        }
    }
    
    func getPersonDetail(userId:String, completionHandler: @escaping(PeopleResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["userId" : userId]
                AF.request(EndPoints.GET_PERSON_DETAIL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        do{
                            if(response.data == nil){
                                let parsedData = PeopleResponse()
                                parsedData.error = "Unknown error has occurred"
                                completionHandler(parsedData)
                            }else{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(PeopleResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }
                        }catch{
                            let parsedData = PeopleResponse()
                            parsedData.error = "Unknown error has occurred"
                            completionHandler(parsedData)
                        }
                }
            }
        }
        
    }
}
