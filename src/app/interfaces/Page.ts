export interface Page {
    url: string;
    maxPage: number;
    page: (string | number);
    queryParams: object;
    active?: boolean;
    disabled?: boolean;
}