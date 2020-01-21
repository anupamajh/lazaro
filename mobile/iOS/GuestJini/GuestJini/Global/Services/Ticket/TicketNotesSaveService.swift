//
//  TicketNotesSaveService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 20/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class TicketNotesSaveService:ObservableObject{
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
    }
    
    func saveTicket(taskNote:TaskNote, completionHandler: @escaping(TaskNotesResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let jsonEncoder = JSONEncoder()
                let taskTicketData = try! jsonEncoder.encode(taskNote)
                let json = try! JSONSerialization.jsonObject(with: taskTicketData, options: []) as? [String : Any]
                 AF.request(EndPoints.TASK_NOTES_SAVE_URL, method: .post, parameters: json,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            let parsedData =  try jsonDecoder.decode(TaskNotesResponse.self, from: response.data!)
                            completionHandler(parsedData)
                        }catch{
                            let parsedData = TaskNotesResponse()
                            parsedData.error = "Unknow error has occrred"
                            parsedData.success = false;
                            completionHandler(parsedData)
                        }
                        
                }
                
                
            }
        }
    }
}

