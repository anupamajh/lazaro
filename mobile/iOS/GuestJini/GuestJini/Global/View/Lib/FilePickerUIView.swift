//
//  FilePickerUIView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 17/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct FilePickerUIView: View {
    var fileURL: URL?
    var body: some View {
        HStack {
            if(self.fileURL != nil){
                Text("\(fileURL!.lastPathComponent)")
            }
            Spacer()
        }
    }
}

struct FilePickerUIView_Previews: PreviewProvider {
    static var previews: some View {
        FilePickerUIView()
    }
}
