//
//  GroupConversationListService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class GroupConversationListService:ObservableObject{
    @Published var groupConversationResponse = GroupConversationResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter,groupId:String) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getGroupConversation(groupId:groupId) { (response) in
            self.groupConversationResponse = response
            self.fetchComplete = true
        }
    }
    
    func reload(groupId:String) -> Void {
        self.fetchComplete = false
        self.getGroupConversation(groupId:groupId) { (response) in
            self.groupConversationResponse = response
            self.fetchComplete = true
        }
    }
    
    func getGroupConversation(groupId:String, completionHandler: @escaping(GroupConversationResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["groupId" : groupId]
                AF.request(EndPoints.GET_GROUP_CONVERSATION, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        do{
                            if(response.data == nil){
                                let parsedData = GroupConversationResponse()
                                parsedData.error = "Unknown error has occurred"
                                completionHandler(parsedData)
                            }else{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(GroupConversationResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }
                        }catch{
                            let parsedData = GroupConversationResponse()
                            parsedData.error = "Unknown error has occurred"
                            completionHandler(parsedData)
                        }
                }
            }
        }
        
    }
}
