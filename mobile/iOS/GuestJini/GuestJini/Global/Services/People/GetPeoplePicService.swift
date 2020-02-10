//
//  GetPeoplePicService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 10/02/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI
import DACache

class GetPeoplePicService: ObservableObject{
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    @Published var peoplePic:Image? = Image(systemName: "person.fill").renderingMode(.original)
    @Published var isLoading: Bool = true
    
    var dataRequest:DataRequest? = nil
    
    let imageCache = NSCache<NSString, UIImage>()
    let cache = DACache.shared.primaryCache
    
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        checkTokenService = CheckTokenService(viewRouter: viewRouter)
    }
    
    func load(userId:String, logoPath:String) -> Void {
        if(logoPath.trimmingCharacters(in: .whitespacesAndNewlines) != ""){
            getPeoplePic(userId: userId, logoPath: logoPath) { (response) in
                self.peoplePic = response
                self.isLoading = false
            }
        }else{
            self.isLoading = false
        }
    }
    
    func cancel() -> Void {
        checkTokenService.cancel()
        if(self.dataRequest != nil){
            self.dataRequest?.cancel()
        }
    }
    
    func getPeoplePic(userId:String, logoPath:String, completionHandler: @escaping(Image)->Void) -> Void {
        let theFileName = (logoPath as NSString).lastPathComponent
        if let cachedImage = cache.load(key: "PEOPLE_" + theFileName) {
            completionHandler(Image(uiImage: UIImage(data: cachedImage)!).renderingMode(.original))
        }else{
            checkTokenService.CheckToken { (checkStatus) in
                if(checkStatus){
                    let headers: HTTPHeaders = [
                        "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                        "Accept": "application/json"
                    ]
                    
                    let parameters = ["userId" : userId]
                    self.dataRequest =  AF.request(EndPoints.GET_PEOPLE_PIC, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                        
                        .responseData { (response) in
                            self.isLoading = false
                            if(response.data != nil){
                                let image = UIImage(data: response.data!)
                                if(image != nil){
                                    self.cache.save(key: "PEOPLE_" + theFileName, value: response.data as NSData?)
                                    completionHandler(Image(uiImage: image!).renderingMode(.original))
                                }else{
                                    completionHandler(Image(systemName: "person.fill").renderingMode(.original))
                                }
                            }else{
                                completionHandler(Image(systemName: "person.fill").renderingMode(.original))
                            }
                            
                    }
                }
            }
        }
    }
    
}



