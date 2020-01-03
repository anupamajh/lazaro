//
//  GuestJiniSearchBox.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 31/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniSearchBox: View {
    @Binding var text:String
    var body: some View {
        VStack{
            TextField("Search", text: $text)
        }
        .padding(.all,5)
        .font(Fonts.RobotRegular)
        .background(Color.white
        .shadow(radius: 10))
        .cornerRadius(25)
        .overlay(
            RoundedRectangle(cornerRadius: 25)
                .stroke(Color("veryLightPink"), lineWidth: 1)
        )
        
    }
}

struct GuestJiniSearchBox_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniSearchBox(text: .constant(""))
    }
}
