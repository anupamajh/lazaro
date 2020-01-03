//
//  GuestJiniRoundButtonSystemImage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 31/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniRoundButtonSystemImage: View {
     var systemImage: String
       var body: some View {
           VStack{
            Image(systemName: systemImage)
               .resizable()
               .padding()
           }.frame(width: 40, height: 40, alignment: .center)
        .foregroundColor(Color.white)
        .background(Color("aquaMarine"))
        .clipped()
        .clipShape(Circle())
        .shadow(radius: 5)
        
       }
}

struct GuestJiniRoundButtonSystemImage_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniRoundButtonSystemImage(systemImage: "chevron.right")
    }
}
