//
//  GetProfilePicService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 15/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI
import DACache

class GetProfilePicService: ObservableObject{
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    @Published var profilePic:Image? = Image(systemName: "camera")
    @Published var isLoading: Bool = true
    
    let cache = DACache.shared.primaryCache
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        checkTokenService = CheckTokenService(viewRouter: viewRouter)
        getProfilePic { (response) in
            self.profilePic = response
        }
    }
    
    func getProfilePic(completionHandler: @escaping(Image)->Void) -> Void {
        if let cachedImage = cache.load(key: "MYPIC") {
            completionHandler(Image(uiImage: UIImage(data: cachedImage)!))
        }else{
            checkTokenService.CheckToken { (checkStatus) in
                if(checkStatus){
                    let headers: HTTPHeaders = [
                        "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                        "Accept": "application/json"
                    ]
                    
                    AF.request(EndPoints.GET_PROFILE_PIC_URL,
                               method: .get,
                               encoding: JSONEncoding.default,
                               headers: headers
                    )
                        .responseData { (response) in
                            
                            self.isLoading = false
                            if(response.data != nil){
                                let str = String(decoding: response.data!, as: UTF8.self)
                                let image = ImageConverter.base64ToImage(str)
                                self.cache.save(key: "MYPIC", value: image?.pngData() as NSData?)
                                if(image != nil){
                                    completionHandler(Image(uiImage: image!))
                                }else{
                                    completionHandler(Image(systemName: "camera"))
                                }
                            }else{
                                completionHandler(Image(systemName: "camera"))
                            }
                    }
                }
            }
        }
    }
    
}
