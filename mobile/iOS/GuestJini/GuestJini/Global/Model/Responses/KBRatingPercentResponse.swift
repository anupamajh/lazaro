//
//  KBRatingPercentResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 22/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class KBRatingPercentResponse: Decodable {
    public var likedPercent:Double? = 0
    public var disLikedPercent:Double?  = 0
    public var likedCount:Int = 0
    public var disLikedCount:Int = 0
    public var success:Bool = false
    public var error:String = ""
}
