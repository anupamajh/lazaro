//
//  GuestJiniRegularTextBox.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 02/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniRegularTextBox: View {
    var placeHolderText:String
    @Binding var text:String
    var body: some View {
        VStack{
            TextField(placeHolderText, text: $text)
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

struct GuestJiniRegularTextBox_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniRegularTextBox(placeHolderText: "Hello",text: .constant(""))
    }
}
