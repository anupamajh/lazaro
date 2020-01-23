//
//  InterestCategoryResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 23/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class InterestCategoryResponse: Decodable {
    public var interestCategory:InterestCategory? = nil
    public var interestCategoryList:[InterestCategory]?  = nil
    public var totalPages:Int = 0
    public var totalRecords:Int = 0
    public var currentRecords:Int = 0
    public var success:Bool = false
    public var error:String = ""
}
