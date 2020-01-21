//
//  TaskNotesResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 20/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class TaskNotesResponse: Decodable {
    public var taskNote:TaskNote? = nil
    public var taskNoteList:[TaskNote]?  = nil
    public var totalPages:Int = 0
    public var totalRecords:Int = 0
    public var currentRecords:Int = 0
    public var success:Bool = false
    public var error:String = ""
}
