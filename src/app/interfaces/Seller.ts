import { User } from './User.modal';

export interface Seller {
    id?: number;
    user: User;
    firstName: string;
    lastName: string;
    type: string;
    phoneNumber: string;
    createAccount: Date;
    isPremium?: boolean;
    premiumAccount: Date;
    lastAddvertisement: Date;
    nextAddvertisment?: Date;
    canCreateAddvertisement?: boolean;
}
