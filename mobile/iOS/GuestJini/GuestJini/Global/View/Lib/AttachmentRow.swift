//
//  AttachmentRow.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 17/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct AttachmentRow: View {
    @State var taskAttachment:TaskAttachment
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                HStack{
                    VStack{
                        HStack{
                            Text(self.taskAttachment.name!)
                                .font(Fonts.RobotRegularSmallText)
                                .foregroundColor(Color("brownishGrey"))
                            Spacer()
                        }
                        HStack{
                            Text("\(self.taskAttachment.size) KB")
                                .font(Fonts.RobotRegularSmallText)
                                .foregroundColor(Color("brownishGrey"))
                            Spacer()
                        }
                        
                    }
                    Spacer()
                   /* Button(action:{}){
                        VStack{
                            Image(systemName: "person")
                                .resizable()
                                .padding(.all, 4)
                        }.frame(width: 18, height: 18, alignment: .center)
                            .foregroundColor(Color.white)
                            .background(Color("aquaMarine"))
                            .clipped()
                            .clipShape(Circle())
                            .shadow(radius: 5)
                    }*/
                    
                }.padding()
                    .background(Color("whiteThree"))
                    .shadow(radius: 10)
                    .cornerRadius(5)
                    .overlay(
                        RoundedRectangle(cornerRadius:5)
                            .stroke(Color("veryLightPink"), lineWidth: 1)
                )
            }.padding()
            
        }
    }
}

struct AttachmentRow_Previews: PreviewProvider {
    
    static var previews: some View {
        AttachmentRow(taskAttachment: TaskAttachment())
    }
}
