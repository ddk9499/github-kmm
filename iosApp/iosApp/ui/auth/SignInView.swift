//
//  SignInView.swift
//  iosApp
//
//  Created by Dostonbek Kamalov on 12/12/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import SwiftUI
import BetterSafariView
struct SignInView : View {
    
    @State private var startingWebAuthenticationSession = false
    
    var body: some View {
        Button(action: {
            self.startingWebAuthenticationSession = true
        }) {
            Text("OAuth via Github")
                .padding()
                .foregroundColor(.white)
                .background(Color.blue)
                .cornerRadius(8)
        }
        .shadow(color: Color.blue, radius: 20, y: 5)
        .webAuthenticationSession(isPresented: $startingWebAuthenticationSession) {
            let oAuthParams = SDK.shared.oAuthParams
            let url = "https://github.com/login/oauth/authorize?client_id=\(oAuthParams.clientId)&redirect_uri=\(oAuthParams.redirectUri)&scope=\(oAuthParams.scope)&state=\(oAuthParams.scope)"
            return WebAuthenticationSession(
                url: URL(string: url)!,
                callbackURLScheme: String(oAuthParams.redirectUri.substring(to: oAuthParams.redirectUri.firstIndex(of: ":")!))
            ) { (callbackURL: URL?, error: Error?) in
                if let url = callbackURL {
                    SDK.shared.loginInteractor.login(redirectedUri: url.absoluteString) { response, error in
                        if let resp = response {
                            print("ios response: \(resp)")
                        }
                        if let err = error {
                            print(err)
                        }
                    }
                }
            }
            .prefersEphemeralWebBrowserSession(false)
        }
    }
}
