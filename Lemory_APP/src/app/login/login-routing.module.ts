import { NgModule } from "@angular/core";
import { Routes } from "@angular/router";
import { NativeScriptRouterModule } from "nativescript-angular/router";

import { LoginComponent } from "./login.component";
import {RegisterComponent} from "~/app/register/register.component";

const routes: Routes = [
    { path: "", component: LoginComponent },
    { path: "register", component: RegisterComponent },
];

@NgModule({
    imports: [NativeScriptRouterModule.forChild(routes)],
    exports: [NativeScriptRouterModule]
})

export class LoginRoutingModule { }


