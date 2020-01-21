//
//  TicketNotesListService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 20/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class TicketNotesListService:ObservableObject{
    @Published var taskNotesResponse = TaskNotesResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var taskNotesList:[TaskNote] = []
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter, ticketId:String) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getNotesList(ticketId: ticketId) { (response) in
            self.taskNotesResponse = response
            self.taskNotesList = response.taskNoteList!;
            self.fetchComplete = true
        }
        
    }
    
    func getNotesList(ticketId:String, completionHandler: @escaping(TaskNotesResponse)->Void) -> Void {
         checkTokenService.CheckToken { (checkStatus) in
             if(checkStatus){
                 let headers: HTTPHeaders = [
                     "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                     "Accept": "application/json"
                 ]
                 
                 let parameters = ["ticketId" : ticketId]
                 AF.request(EndPoints.TASK_NOTES_GET_BY_TICKET_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                     .responseData { (response) in
                         let jsonDecoder = JSONDecoder()
                         let parsedData = try! jsonDecoder.decode(TaskNotesResponse.self, from: response.data!)
                         completionHandler(parsedData)
                 }
             }
             
         }
     }
    
    
    
}
