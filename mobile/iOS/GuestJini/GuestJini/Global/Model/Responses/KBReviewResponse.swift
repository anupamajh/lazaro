//
//  KBReviewResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 22/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class KBReviewResponse: Decodable {
    public var kbReview:KBReview? = nil
    public var kbReviewList:[KBReview]?  = nil
    public var totalPages:Int = 0
    public var totalRecords:Int = 0
    public var currentRecords:Int = 0
    public var success:Bool = false
    public var error:String = ""
}
