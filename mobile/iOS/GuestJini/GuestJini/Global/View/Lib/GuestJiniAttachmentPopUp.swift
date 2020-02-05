//
//  GuestJiniAttachmentPopUp.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 05/02/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniAttachmentPopUp: View {
    @Binding var showAttachmentList: Bool
    var attachmentList:[TaskAttachment]
    
    var body: some View {
        VStack{
            VStack{
                ScrollView{
                    ForEach(self.attachmentList){
                        attachment in
                        VStack{
                            VStack{
                                AttachmentRow(taskAttachment: attachment)
                            }.padding(.vertical)
                        } .padding(.vertical)
                    }
                }
                HStack{
                    Spacer()
                    Button(action: {
                        self.showAttachmentList = false
                        
                    }) {
                        GuestJiniRoundButtonSystemImage(systemImage: "checkmark")
                    }
                    
                    Spacer()
                }.padding()
            }.background(Color.white)
                .cornerRadius(15)
                .padding(.all,15)
            
        }.shadow(radius: 10)
            .padding()
            .opacity(1)
        
    }
}
