//
//  GetInterestCategoryListService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 23/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class GetInterestCategoryListService:ObservableObject{
    @Published var interestCategoryResponse = InterestCategoryResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var interestCategoryList:[InterestCategory] = []
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getKBList { (response) in
            self.interestCategoryResponse = response
            self.interestCategoryList = response.interestCategoryList!;
            self.fetchComplete = true
        }
    }
    
    func getKBList(completionHandler: @escaping(InterestCategoryResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["" : ""]
                AF.request(EndPoints.GET_INTEREST_CATEGORY_LIST_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            let parsedData = try jsonDecoder.decode(InterestCategoryResponse.self, from: response.data!)
                            completionHandler(parsedData)
                        }catch{
                            let parsedData = InterestCategoryResponse()
                            completionHandler(parsedData)
                        }
                        
                }
            }
            
        }
    }
}
