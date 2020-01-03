//
//  GuestJiniImageCard.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniImageCard: View {
    var image:String
    var body: some View {
        VStack{
            Image(image)
                .resizable()
                .clipped()
                .clipShape(Rectangle())
        }
    }
}

struct GuestJiniImageCard_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniImageCard(image: "image2")
    }
}

/*

 .resizable()
 .clipped()
 .colorMultiply(Color(red: 0.09, green: 0.286, blue: 0.486, opacity: 0.8))
 .edgesIgnoringSafeArea(.top)
 .aspectRatio(contentMode: .fill)
 .animation(.easeIn)
 .transition(.rightToLeft)
 */
