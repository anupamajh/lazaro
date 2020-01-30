//
//  UploadTicketAttachmentService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 17/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class UploadTicketAttachmentService: ObservableObject{
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        checkTokenService = CheckTokenService(viewRouter: viewRouter)
    }
    
    func uploadAttachmentURL(fileURL:Data?, fileName:String, completionHandler: @escaping(TaskAttachmentResponse) -> Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)"
                ]
                
                AF.upload(multipartFormData: { multiPart in
                    multiPart.append(fileName.data(using: .utf8)!, withName: "fileName")
                    multiPart.append(fileURL!, withName: "file", fileName: fileName)
                }, to: EndPoints.TICKET_ATTACHMENT_URL, method: .post, headers: headers)
                    .uploadProgress(queue: .main, closure: { progress in
                        //Current upload progress of file
                        print("Upload Progress: \(progress.fractionCompleted)")
                    }) .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            if(response.data != nil){
                                let parsedData =  try jsonDecoder.decode(TaskAttachmentResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }else{
                                let parsedData = TaskAttachmentResponse()
                                parsedData.error = "Unknow error has occrred"
                                parsedData.success = false;
                                completionHandler(parsedData)
                            }
                        }catch{
                            let parsedData = TaskAttachmentResponse()
                            parsedData.error = "Unknow error has occrred"
                            parsedData.success = false;
                            completionHandler(parsedData)
                        }
                        
                }
            }
        }
    }
}
