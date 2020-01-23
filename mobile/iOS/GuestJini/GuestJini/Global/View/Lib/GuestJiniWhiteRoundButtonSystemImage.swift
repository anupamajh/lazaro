//
//  GuestJiniWhiteRoundButtonSystemImage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 22/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniWhiteRoundButtonSystemImage: View {
   var systemImage: String
    var body: some View {
        VStack{
         Image(systemName: systemImage)
            .resizable()
             .padding(.all, 11)
        }.frame(width: 40, height: 40, alignment: .center)
     .foregroundColor(Color.black)
            .background(Color.white)
     .clipped()
     .clipShape(Circle())
     .shadow(radius: 5)
     
    }
}

struct GuestJiniWhiteRoundButtonSystemImage_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniWhiteRoundButtonSystemImage(systemImage: "person.fill")
    }
}
