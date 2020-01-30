//
//  GroupDetailService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class GroupDetailService:ObservableObject{
    @Published var groupResponse = GroupResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var group:GroupModel = GroupModel()
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter, groupId:String) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getGroup(groupId: groupId) { (response) in
            self.group = response.group!
            self.groupResponse = response
            self.fetchComplete = true
        }
    }
    
    func reload(groupId:String) -> Void {
         self.getGroup(groupId: groupId) { (response) in
                   self.group = response.group!
                   self.groupResponse = response
                   self.fetchComplete = true
               }
    }
    
    func getGroup(groupId:String, completionHandler: @escaping(GroupResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let parameters = [
                    "id" : groupId
                ]
                AF.request(EndPoints.GET_GROUP_DETAILS, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        if(response.data != nil){
                            do{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(GroupResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }catch{
                                let parsedData = GroupResponse()
                                parsedData.error = "Unknow error has occurred!"
                                completionHandler(parsedData)
                            }
                        }else{
                            let parsedData = GroupResponse()
                            parsedData.error = "Unknow error has occurred!"
                            completionHandler(parsedData)
                        }
                }
            }
        }
    }
}
