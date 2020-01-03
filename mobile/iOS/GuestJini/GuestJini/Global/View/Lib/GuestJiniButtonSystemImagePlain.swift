//
//  GuestJiniButtonSystemImagePlain.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniButtonSystemImagePlain: View {
    var imageName: String
    var body: some View {
        VStack{
            Image(systemName: imageName)
            .resizable()
                .foregroundColor(Color.black)
        }.frame(width: 25, height: 20, alignment: .center)
    }
}

struct GuestJiniButtonSystemImagePlain_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
    }
}
