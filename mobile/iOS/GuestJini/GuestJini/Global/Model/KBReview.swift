//
//  KBReview.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 22/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class KBReview: Codable, Identifiable, ObservableObject {
    public var id:String? = nil
    public var clientId:String?  = nil
    public var orgId:String? = nil
    public var kbId:String? = nil
    public var reviewBy:String? = nil
    public var reviewByName:String? = nil
    public var reviewComment:String? = nil
    public var reviewByLogoPath:String? = nil
    public var creationTime:String? = ""
}
