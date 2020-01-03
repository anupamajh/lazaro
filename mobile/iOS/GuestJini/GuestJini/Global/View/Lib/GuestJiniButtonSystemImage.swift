//
//  GuestJiniButtonSystemImage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniButtonSystemImage: View {
    var imageName: String
    var body: some View {
        VStack{
            Image(systemName: imageName)
            .resizable()
            .padding(.top, 12)
                .padding(.bottom, 12)
                .padding(.leading, 30)
                .padding(.trailing, 30)
                .foregroundColor(Color.white)
            .background(Color("aquaMarine"))
            .cornerRadius(18)
        }.frame(width: 100, height: 50, alignment: .center)
    }
}

struct GuestJiniButtonSystemImage_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniButtonSystemImage(imageName: "arrow.right")
    }
}
