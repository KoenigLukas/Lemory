import {Component, OnInit} from "@angular/core";

@Component({
    selector: "Login",
    moduleId: module.id,
    templateUrl: "./login.component.html"
})
export class LoginComponent implements OnInit {

    constructor() {
        // Use the component constructor to inject providers.
    }

    ngOnInit(): void {
        // Init your component properties here.
    }

    onButtonTap(): void {
        console.log("Button was pressed");
    }

    login(): void {
        console.log("Button was pressed");
    }


    progressValue: number = 80;
    currentDay: number = new Date().getDate();
}
