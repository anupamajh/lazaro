//
//  GetKBAuthorPicService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 22/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class GetKBAuthorPicService: ObservableObject{
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    @Published var kbAuthorPic:Image? = Image(systemName: "person.fill")
    @Published var isLoading: Bool = true
    
    init(viewRouter: ViewRouter, kbId:String) {
        self.viewRouter = viewRouter;
        checkTokenService = CheckTokenService(viewRouter: viewRouter)
        getKbAuthorPic(kbId:kbId) { (response) in
            self.kbAuthorPic = response
            self.isLoading = false
        }
    }
    
    func getKbAuthorPic(kbId:String, completionHandler: @escaping(Image)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["id" : kbId]
                AF.request(EndPoints.KB_GET_AUTHOR_PIC, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        self.isLoading = false
                        if(response.data != nil){
                            let str = String(decoding: response.data!, as: UTF8.self)
                            let image = ImageConverter.base64ToImage(str)
                            if(image != nil){
                                completionHandler(Image(uiImage: image!))
                            }else{
                                completionHandler(Image(systemName: "person.fill"))
                            }
                        }else{
                            completionHandler(Image(systemName: "person.fill"))
                        }
                        
                }
            }
        }
    }
    
}
