//
//  GuestJiniTextArea.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniTextArea: View {
    var placeHolderText:String
    @Binding var text:String
    var body: some View {
        VStack{
            
                TextField(self.placeHolderText, text: self.$text)
                    .frame(minWidth: 100, maxWidth: .infinity, minHeight: 100, maxHeight: .infinity, alignment: .topLeading)
                    .multilineTextAlignment(.leading)
                    .lineLimit(10)
        }
        .padding(.all,10)
        .font(Fonts.RobotRegular)
        .background(Color.white
        .shadow(radius: 10))
        .cornerRadius(25)
        .overlay(
            RoundedRectangle(cornerRadius:5)
                .stroke(Color("veryLightPink"), lineWidth: 1)
        )
    }
    
}

struct GuestJiniTextArea_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniTextArea(placeHolderText: "Hello",text: .constant(""))
    }
}
