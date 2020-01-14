//
//  GuestJiniEditIconButton.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 14/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniEditIconButton: View {
   var systemImage: String
    var body: some View {
        VStack{
         Image(systemName: systemImage)
            .resizable()
            .padding(.all,5)
        }.frame(width: 20, height: 20, alignment: .center)
     .foregroundColor(Color.white)
     .background(Color("aquaMarine"))
     .clipped()
     .clipShape(Circle())
     .shadow(radius: 5)
     
    }
}

struct GuestJiniEditIconButton_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniEditIconButton(systemImage: "pencil")
    }
}
