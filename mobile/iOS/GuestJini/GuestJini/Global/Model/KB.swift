//
//  KB.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 21/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class KB: Codable, Identifiable, ObservableObject {
    public var id:String? = ""
    public var clientId:String?  = ""
    public var orgId:String? = ""
    public var topicTitle:String? = ""
    public var topicNarration:String? = ""
    public var authorName:String? = ""
    public var authorLogoPath:String? = ""
    public var creationTime:String? = ""
}
