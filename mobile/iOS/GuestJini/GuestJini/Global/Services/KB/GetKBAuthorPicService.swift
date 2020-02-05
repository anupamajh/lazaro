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
    
    var dataRequest:DataRequest? = nil
    
    let imageCache = NSCache<NSString, UIImage>()
    
    
    init(viewRouter: ViewRouter, kbId:String) {
        self.viewRouter = viewRouter;
        checkTokenService = CheckTokenService(viewRouter: viewRouter)
        /*getKbAuthorPic(kbId:kbId) { (response) in
         self.kbAuthorPic = response
         self.isLoading = false
         }*/
    }
    
    func cancel() -> Void {
        checkTokenService.cancel()
        if(self.dataRequest != nil){
            self.dataRequest?.cancel()
        }
    }
    
    func getKbAuthorPic(kbId:String, completionHandler: @escaping(Image)->Void) -> Void {
        if let cachedImage = self.imageCache.object(forKey: kbId as NSString) {
            completionHandler(Image(uiImage: cachedImage))
        }else{
            checkTokenService.CheckToken { (checkStatus) in
                if(checkStatus){
                    let headers: HTTPHeaders = [
                        "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                        "Accept": "application/json"
                    ]
                    
                    let parameters = ["id" : kbId]
                    self.dataRequest =  AF.request(EndPoints.KB_GET_AUTHOR_PIC, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                        
                        .responseData { (response) in
                            self.isLoading = false
                            if(response.data != nil){
                                let image = UIImage(data: response.data!)
                                self.imageCache.setObject(image!, forKey: kbId as NSString)
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
    
}
