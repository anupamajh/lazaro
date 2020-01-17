//
//  AttachmentModel.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 17/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation

struct AttachmentModel:Identifiable {
    var id:UUID = UUID()
    var sourceURL:URL? = nil
    var sourceData:Data? = nil
    var fileName:String = ""
    var fileType:Int = 0;
    
}
