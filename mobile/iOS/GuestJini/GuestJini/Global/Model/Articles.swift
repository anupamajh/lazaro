//
//  Articles.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 31/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import Foundation

struct Article: Codable {
    var id = UUID()
    var author:String = ""
    var publishedDate:String = ""
    var excerpts:String = ""
    var articleText:String = ""
}
