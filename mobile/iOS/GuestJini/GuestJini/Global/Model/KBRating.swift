//
//  KBRating.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 22/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class KBRating: Codable, Identifiable, ObservableObject {
    public var id:String? = nil
    public var clientId:String?  = nil
    public var orgId:String? = nil
    public var kbId:String? = nil
    public var ratingBy:String? = nil
    public var isLiked:Int? = nil
    public var creationTime:String? = ""
}
