//
//  GuestJiniSquareButtonSystemImage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 31/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniSquareButtonSystemImage: View {
   var systemImage: String
    var body: some View {
        VStack{
         Image(systemName: systemImage)
            .resizable()
            .padding(.all, 5)
        }.frame(width: 40, height: 40, alignment: .center)
     .foregroundColor(Color.white)
     .background(Color("aquaMarine"))
     .clipped()
     .clipShape(Rectangle())
     .shadow(radius: 5)
     
    }
}

struct GuestJiniSquareButtonSystemImage_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniSquareButtonSystemImage(systemImage: "slider.horizontal.3")
    }
}
