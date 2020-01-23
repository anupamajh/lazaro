//
//  GetInterestListService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 23/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class GetInterestListService:ObservableObject{
    @Published var interestResponse = InterestResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var interestList:[Interest] = []
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getKBList { (response) in
            self.interestResponse = response
            self.interestList = response.interestList!;
            self.fetchComplete = true
        }
    }
    
    func getKBList(completionHandler: @escaping(InterestResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["" : ""]
                AF.request(EndPoints.GET_INTEREST_LIST_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            let parsedData = try jsonDecoder.decode(InterestResponse.self, from: response.data!)
                            completionHandler(parsedData)
                        }catch{
                            let parsedData = InterestResponse()
                            completionHandler(parsedData)
                        }
                        
                }
            }
            
        }
    }
}
