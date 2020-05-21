import { RegisterUser } from './RegisterUser.model';

export interface SellerPost {
    authId?: number;
    firstName: string;
    lastName: string;
    phoneNumber: string;
    registerUser?: RegisterUser;
}
